package com.vladnickgofj.hotel.service.impl;

import com.vladnickgofj.hotel.controller.dto.HotelDto;
import com.vladnickgofj.hotel.controller.dto.PagenableElementsDto;
import com.vladnickgofj.hotel.dao.HotelDao;
import com.vladnickgofj.hotel.dao.entity.Hotel;
import com.vladnickgofj.hotel.service.HotelService;
import com.vladnickgofj.hotel.service.mapper.Mapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HotelServiceImplTest {
    private final HotelDao hotelRepository = Mockito.mock(HotelDao.class);
    private final Mapper<HotelDto, Hotel> mapper = Mockito.mock(Mapper.class);
    private final HotelService hotelService = new HotelServiceImpl(hotelRepository, mapper);

    @ParameterizedTest(name="[{index}] {3}")
    @CsvFileSource(resources = "/paginateDtoAndSizeSource.csv")
    void getPagesTest(int size, int hotelsOnPage, int expected, String description) {
        Mockito.when(hotelRepository.countAll()).thenReturn(size);
        PagenableElementsDto pagenableElementsDto = PagenableElementsDto.newBuilder().itemsOnPage(hotelsOnPage).build();
        Integer actual = hotelService.getNumberOfPages(pagenableElementsDto);
        assertEquals(expected, actual);
    }

}


