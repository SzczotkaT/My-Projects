package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TrelloMapperTestSuite {


    TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoards(){
        //Given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("test_id1", "test_first", new ArrayList<>()));

        //When
        List<TrelloBoard> mappedToBoard = trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        assertEquals(1, mappedToBoard.size());
        assertEquals("test_id1", mappedToBoard.get(0).getId());

    }

    @Test
    public void testmapToBoardsDto(){
        //Given
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard("test_id2", "test_second", new ArrayList<>()));

        //When
        List<TrelloBoardDto> mappedToBoardDto = trelloMapper.mapToBoardsDto(trelloBoard);

        //Then
        assertEquals(1, mappedToBoardDto.size());
        assertEquals("test_id2", mappedToBoardDto.get(0).getId());

    }

    @Test
    public void testmapToList(){
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("test_id3", "test_third", false));

        //When
        List<TrelloList> mappedToList = trelloMapper.mapToList(trelloListDto);

        //Then
        assertEquals(1, mappedToList.size());
        assertEquals("test_id3", mappedToList.get(0).getId());
    }

    @Test
    public void testmapToListDto(){
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("test_id4", "test_fourth", false));

        //When
        List<TrelloListDto> mappedToListDto = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(1, mappedToListDto.size());
        assertEquals("test_id4", mappedToListDto.get(0).getId());

    }

    @Test
    public void testmapToCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("test_fifth", "testing mapping to CardDto", "1", "test_listId");

        //When
        TrelloCardDto mappedToCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("test_fifth", mappedToCardDto.getName());
        assertEquals("testing mapping to CardDto", mappedToCardDto.getDescription());
    }

    @Test
    public void testmapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_sixth", "testing mapping to Card", "1", "test_listId");

        //When
        TrelloCard mappedToCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("test_sixth", mappedToCard.getName());
        assertEquals("testing mapping to Card", mappedToCard.getDescription());

    }
}
