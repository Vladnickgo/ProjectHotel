package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.*;
import com.vladnickgofj.hotel.dao.BookingDao;
import com.vladnickgofj.hotel.dao.entity.Booking;
import com.vladnickgofj.hotel.dao.entity.RoomStatus;
import com.vladnickgofj.hotel.service.BookingService;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import com.vladnickgofj.hotel.service.util.BookingRequestDtoUtil;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {

    private static final Integer STATUS_BOOKED = 2;
    private final BookingDao bookingRepository;
    private final Mapper<BookingDto, Booking> bookingMapper;
    private final Mapper<RoomStatusDto, RoomStatus> roomStatusMapper;
    private static final Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);
    private BookingRequestDtoUtil bookingRequestDtoUtil;

    public BookingServiceImpl(BookingDao bookingRepository, Mapper<BookingDto, Booking> bookingMapper, Mapper<RoomStatusDto, RoomStatus> roomStatusMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.roomStatusMapper = roomStatusMapper;
    }

    @Override
    public BookingDto getBookingByParameters(BookingDto bookingDto) {
        Booking bookingByParameters = bookingRepository.findBookingByParameters(bookingDto);
        return bookingMapper.mapEntityToDto(bookingByParameters);
    }

    @Override
    public BookingDto findById(Integer bookingId) {
        return bookingMapper.mapEntityToDto(bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("No items selected")));
    }

    @Override
    public void addBookingPayment(BookingDto bookingServiceById) {
        if (bookingServiceById.getBookingStatusId().equals(STATUS_BOOKED)) {
            throw new IllegalArgumentException("Payment already exist");
        }
        bookingRepository.addBookingPayment(bookingServiceById);
    }

    @Override
    public List<BookingDto> findBookingsByUserIdAndParameters(BookingRequestDto bookingRequestDto) {
        bookingRequestDtoUtil = new BookingRequestDtoUtil(bookingRequestDto);
        Integer firstRecordOnPage = getFirstRecordOnPage(bookingRequestDto);
        LOGGER.info("firstRecordOnPage: " + firstRecordOnPage);
        Comparator<BookingDto> comparator = bookingRequestDtoUtil.extractedComparator();
        return bookingRepository.findBookingsByUserIdAndParameters(bookingRequestDto, firstRecordOnPage)
                .stream()
                .map(bookingMapper::mapEntityToDto)
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    @Override
    public Integer countAll(BookingRequestDto bookingRequestDto) {
        return bookingRepository.countAll(bookingRequestDto);
    }

    private Integer getFirstRecordOnPage(BookingRequestDto bookingRequestDto) {
        BookingRequestDtoUtil bookingRequestDtoUtil = new BookingRequestDtoUtil(bookingRequestDto);
        return bookingRequestDtoUtil.getItemsOnPage() * (bookingRequestDtoUtil.getNumberOfPage() - 1);
    }

    @Override
    public Integer getNumberOfPages(BookingRequestDto bookingRequestDto) {
        bookingRequestDtoUtil = new BookingRequestDtoUtil(bookingRequestDto);
        Integer size = countAll(bookingRequestDto);
        return size / bookingRequestDtoUtil.getItemsOnPage() + (size % bookingRequestDtoUtil.getItemsOnPage() > 0 ? 1 : 0);
    }

    @Override
    public Integer getNumberOfPage() {
        return bookingRequestDtoUtil.getNumberOfPage();
    }

    @Override
    public void cancelBookingById(BookingDto byId, LocalDate dateEnd) {
        bookingRepository.cancelBookingById(byId, dateEnd);
    }

    @Override
    public void createNewBooking(BookingDto bookingDto, RoomStatusDto roomStatusDto) {
        Booking booking = bookingMapper.mapDtoToEntity(bookingDto);
        RoomStatus roomStatus = roomStatusMapper.mapDtoToEntity(roomStatusDto);
        bookingRepository.createNewBooking(booking, roomStatus);
    }


}


