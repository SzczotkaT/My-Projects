package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void testFetchTrelloBoards(){
        //Given
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "Board1",new ArrayList<>()));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto>  fetchedTrelloBoardDtos = trelloClient.getTrelloBoards();

        //Then
        assertEquals("1", fetchedTrelloBoardDtos.get(0).getId());
    }

    @Test
    public void testCreateTrelloCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test Card", "Testing Card", "First", "1");
        CreatedTrelloCardDto cre = new CreatedTrelloCardDto("1", "Test", "Http://Test.com");
        when(trelloClient.createNewCard(any())).thenReturn(cre);

        //When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("1", result.getId());
    }

}