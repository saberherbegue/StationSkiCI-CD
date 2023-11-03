package com.example.stationski;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.stationski.entities.Moniteur;
import com.example.stationski.repositories.MoniteurRepository;
import com.example.stationski.services.MoniteurServiceImpl;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class SkieurImplTest {
	   @Mock
	    private MoniteurRepository moniteurRepository;

	    @InjectMocks
	    private MoniteurServiceImpl moniteurService;

	    @Test
	    public void testAddMoniteur() {
	        Moniteur mockMoniteur = new Moniteur(1, 12345L, "John", "Doe", LocalDate.of(1990, 1, 1), 10000);
	        when(moniteurRepository.save(any(Moniteur.class))).thenReturn(mockMoniteur);

	        Moniteur result = moniteurService.addMoniteur(mockMoniteur);
	        assertEquals("John", result.getNomM());
	    }

	    @Test
	    public void testRetrieveAllMoniteurs() {
	        Moniteur moniteur1 = new Moniteur(1, 12345L, "John", "Doe", LocalDate.of(1990, 1, 1), 10000);
	        Moniteur moniteur2 = new Moniteur(2, 67890L, "Jane", "Doe", LocalDate.of(1995, 5, 5), 15000);

	        when(moniteurRepository.findAll()).thenReturn(Arrays.asList(moniteur1, moniteur2));

	        List<Moniteur> result = moniteurService.retrieveAllMoniteurs();
	        assertEquals(2, result.size());
	    }

	    @Test
	    public void testRetrieveMoniteur() {
	        Moniteur moniteur = new Moniteur(1, 12345L, "John", "Doe", LocalDate.of(1990, 1, 1), 10000);
	        when(moniteurRepository.findById(1)).thenReturn(Optional.of(moniteur));

	        Moniteur result = moniteurService.retrieveMoniteur(1);
	        assertEquals("John", result.getNomM());
	    }

	    @Test
	    public void testDeleteMoniteur() {
	        moniteurService.deleteMoniteur(1);
	        verify(moniteurRepository, times(1)).deleteById(1);
	    }
	}
