package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;
import com.vladnickgofj.hotel.controller.dto.RoomStatusDto;
import com.vladnickgofj.hotel.dao.RoomStatusDao;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.service.RoomStatusService;
import com.vladnickgofj.hotel.service.mapper.Mapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.vladnickgofj.hotel.validator.ValidatorErrorMessage.ROOM_STATUS_NOT_FOUND;

public class RoomStatusServiceImpl implements RoomStatusService {
    private final RoomStatusDao roomStatusRepository;
    private final Mapper<RoomStatusDto, RoomStatus> mapper;

    public RoomStatusServiceImpl(RoomStatusDao roomStatusRepository, Mapper<RoomStatusDto, RoomStatus> mapper) {
        this.roomStatusRepository = roomStatusRepository;
        this.mapper = mapper;
    }

    @Override
    public RoomStatusDto getRoomStatusById(Integer id) {
        RoomStatus roomStatus = roomStatusRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(ROOM_STATUS_NOT_FOUND, id)));
        return mapper.mapEntityToDto(roomStatus);
    }

    @Override
    public List<RoomStatusDto> findAll(Integer hotelId, RoomStatusDto roomStatusDto, String roomStatusQuerySubstitute, PagenableElementsDto pagenableElementsDto, String sorting, String ordering, Comparator<RoomStatusDto> comparator) {
        Integer itemsOnPage = pagenableElementsDto.getItemsOnPage();
        Integer firstRecordOnPage = getFirstRecordOnPage(pagenableElementsDto, hotelId, roomStatusQuerySubstitute, roomStatusDto);
        return roomStatusRepository.findAll(hotelId, roomStatusDto, roomStatusQuerySubstitute, itemsOnPage, firstRecordOnPage, sorting, ordering)
                .stream()
                .map(mapper::mapEntityToDto)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public Integer countAll(Integer hotelId, String roomStatusQuerySubstitute, RoomStatusDto roomStatusDto) {
        return roomStatusRepository.countAll(hotelId, roomStatusQuerySubstitute, roomStatusDto);
    }

    private Integer getFirstRecordOnPage(PagenableElementsDto pagenableElementsDto, Integer hotelId, String roomStatusQuerySubstitute, RoomStatusDto roomStatusDto) {
        Integer pages = getNumberOfPages(pagenableElementsDto, hotelId, roomStatusQuerySubstitute, roomStatusDto);
        pages = pages == 0 ? 1 : pages;
        return pagenableElementsDto.getItemsOnPage() * ((Math.min(pagenableElementsDto.getNumberOfPage(), pages)) - 1);
    }

    @Override
    public Integer getNumberOfPages(PagenableElementsDto pagenableElementsDto, Integer hotelId, String roomStatusQuerySubstitute, RoomStatusDto roomStatusDto) {
        Integer size = countAll(hotelId, roomStatusQuerySubstitute, roomStatusDto);
        return size / pagenableElementsDto.getItemsOnPage() + (size % pagenableElementsDto.getItemsOnPage() > 0 ? 1 : 0);
    }
}
