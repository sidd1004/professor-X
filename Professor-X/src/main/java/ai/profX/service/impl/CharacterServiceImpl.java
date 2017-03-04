package ai.profX.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ai.profX.model.Character;
import ai.profX.model.Confidence;
import ai.profX.model.repo.CharacterRepo;
import ai.profX.model.repo.ConfidenceRepo;
import ai.profX.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {
	
	@Autowired
	private CharacterRepo characterRepo;
	
	@Autowired
	private ConfidenceRepo confidenceRepo;

	@Override
	public long addNewCharacter(String name) {
		Character character = new Character(name);
		long charId = character.getCharId();
		
		 /* TODO to implement further operations once a new character is created
		 * 1. add the object to the db and get its id
		 * 2. Get all questions
		 * 3. update confidence for all questions with respective question id's and this new charId i.e., call initConfidence
		 */
		return charId;
	}

	@Override
	public List<Character> getAllCharacters() {
		List<Character> charList = new ArrayList<>();
		charList = characterRepo.findAll();
		if(charList.size() > 0)
			return charList;
		else
			return null;
	}

	@Override
	public Character getCharacterByName(String name) {
		Character character = characterRepo.findByName(name);
		if(character!=null)
			return character;
		else
			return null;
	}

	@Override
	public Character getCharacterById(long charId) {
		Character character = characterRepo.findByCharId(charId);
		if(character!=null)
			return character;
		else
			return null;
	}

	@Override
	public int getNoOfUnknownCharactersForQuestionId(List<Character> characters, long questionId) {
		int value = 0;
		int count = 0;
		
		Iterator<Character> it = characters.iterator();
		Confidence confidence = null;
		while(it.hasNext()){
			confidence = confidenceRepo.findByCharacterIdAndQuestionIdAndValue(it.next().getCharId(), questionId, value);
			if(confidence != null)
				count++;
		}
		return count;
	}

	@Override
	public int getNoOfCharactersWithPositiveConfidenceForQuestionId(List<Character> characters, long questionId) {
		int count = 0;
		Iterator<Character> it = characters.iterator();
		Confidence confidence;
		
		while(it.hasNext()){
			confidence = confidenceRepo.findByCharacterIdAndQuestionId(it.next().getCharId(), questionId);
			if(confidence.getValue() > 0){
				count++;
			}
		}
		return count;
	}

	@Override
	public int getNoOfCharactersWithNegativeConfidenceForQuestionId(List<Character> characters, long questionId) {
		int count = 0;
		Iterator<Character> it = characters.iterator();
		Confidence confidence;
		
		while(it.hasNext()){
			confidence = confidenceRepo.findByCharacterIdAndQuestionId(it.next().getCharId(), questionId);
			if(confidence.getValue() < 0){
				count++;
			}
		}
		return count;
	}

	@Override
	public void removeCharacter(long charId) {
		Character character = characterRepo.findByCharId(charId);
		if(character!=null)
			characterRepo.delete(character);
		/*
		 * TODO: to implement further functionality
		 * 1. delete the repective character if it exists
		 * 2. delete all its confidence associations as well
		 */
	}

	@Override
	public void updateNoOfTimesPlayed(long charId) {
		Character character = characterRepo.findByCharId(charId);
		if(character!=null){
			character.setNoOfTimesPlayed(character.getNoOfTimesPlayed() + 1);
		}
	}

	@Override
	public long getTotalCharacterCount() {
		return characterRepo.count();
	}

}
