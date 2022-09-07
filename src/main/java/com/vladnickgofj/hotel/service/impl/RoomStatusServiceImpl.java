package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.RoomStatusDao;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.service.RoomStatusService;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import com.vladnickgofj.hotel.service.util.RoomStatusDtoRequestUtil;
import com.vladnickgofj.hotel.validator.BookingValidator;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.ROOM_STATUS_NOT_FOUND;

public class RoomStatusServiceImpl implements RoomStatusService {
    private final RoomStatusDao roomStatusRepository;
    private final Mapper<RoomStatusDto, RoomStatus> mapper;
    private static final Logger LOGGER = Logger.getLogger(RoomStatusServiceImpl.class);
    private final BookingValidator bookingValidator;
    public RoomStatusServiceImpl(RoomStatusDao roomStatusRepository, Mapper<RoomStatusDto, RoomStatus> mapper, BookingValidator bookingValidator) {
        this.roomStatusRepository = roomStatusRepository;
        this.mapper = mapper;
        this.bookingValidator = bookingValidator;
    }

    @Override
    public RoomStatusDto getRoomStatusById(Integer id) {
        RoomStatus roomStatus = roomStatusRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(ROOM_STATUS_NOT_FOUND, id)));
        return mapper.mapEntityToDto(roomStatus);
    }

    @Override
    public List<RoomStatusDto> findAll(RoomStatusDtoRequestUtil roomStatusDtoRequest) {
        Integer firstRecordOnPage = getFirstRecordOnPage(roomStatusDtoRequest);
        Comparator<RoomStatusDto> comparator = roomStatusDtoRequest.extractedComparator();
        return roomStatusRepository.findAll(roomStatusDtoRequest, firstRecordOnPage)
                .stream()
                .map(mapper::mapEntityToDto)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public Integer countAll(RoomStatusDtoRequestUtil roomStatusDtoRequest) {
        return roomStatusRepository.countAll(roomStatusDtoRequest);
    }

    private @NotNull Integer getFirstRecordOnPage(RoomStatusDtoRequestUtil roomStatusDtoRequest) {
        PagenableElementsDto pagenableElementsDto = roomStatusDtoRequest.getPagenableElementsDto();
        Integer pages = getNumberOfPages(roomStatusDtoRequest);
        pages = pages == 0 ? 1 : pages;
        return pagenableElementsDto.getItemsOnPage() * ((Math.min(pagenableElementsDto.getNumberOfPage(), pages)) - 1);
    }

    @Override
    public Integer getNumberOfPages(RoomStatusDtoRequestUtil roomStatusDtoRequest) {
        PagenableElementsDto pagenableElementsDto = roomStatusDtoRequest.getPagenableElementsDto();
        Integer size = countAll(roomStatusDtoRequest);
        LOGGER.info("size: " + size);
        return size / pagenableElementsDto.getItemsOnPage() + (size % pagenableElementsDto.getItemsOnPage() > 0 ? 1 : 0);
    }

    @Override
    public List<RoomStatusDto> findAllFreeByParameters(UsersOrderDto usersOrderDto) {
        return roomStatusRepository.findAllFreeByParameters(usersOrderDto)
                .stream()
                .map(mapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LocalDate findDateEndForFreeStatusByRoomIdAndDateStart(BookingDto byId) {
        bookingValidator.validate(byId);
        LOGGER.info("findDateEndForFreeStatusByRoomIdAndDateStart: " + roomStatusRepository.findFreeByRoomIdAndDateStart(byId));
        return roomStatusRepository.findFreeByRoomIdAndDateStart(byId);
    }


}
