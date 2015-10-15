package org.firstonlineuniversity.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.payment.Cards;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.utils.EncryptionUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/users/cards")
@PropertySource("classpath:message.properties")
public class AdminCardsControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	static final Logger logger = Logger
			.getLogger(AdminCardsControllerRest.class);

	/*
	 * Add Card Information
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody Cards cards) throws HibernateException,
			Exception {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Accounts accounts = new Accounts();
		accounts.setId(customUser.getId());
		cards.setAccounts(accounts);
		try {
			cards.setCD(new Date());
			cards.setUD(new Date());
			cards.setUB(customUser.getId());
			cards.setCB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String cardNumber = cards.getCardNumber();
		String cardIdentificationNumber = cardNumber.substring(
				cardNumber.length() - 5, cardNumber.length() - 1);
		cards.setCardIdentificationNumber(cardIdentificationNumber);

		// encryption
		cards.setCardNumber(EncryptionUtils.encrypt(cardNumber));
		cards.setSecureCode(EncryptionUtils.encrypt(cards.getSecureCode()));

		dataServices.addEntity(cards);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("cards.add"));

	}

	/*
	 * Get Card Information for payment gateway
	 */
	@RequestMapping(value = "/payment/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Cards payment(@PathVariable long id)
			throws HibernateException, Exception {

		logger.info("ID: " + id);

		Cards cardsDB = (Cards) dataServices.getEntity(id, Cards.class);

		if (cardsDB == null)
			throw new EntityNotFoundException();

		cardsDB.setCardNumber(EncryptionUtils.decrypt(cardsDB.getCardNumber()));
		cardsDB.setSecureCode(EncryptionUtils.decrypt(cardsDB.getSecureCode()));
		
		Cards cards = new Cards(null, cardsDB.getCardNumber(), cardsDB.getExpirationDate(),
				cardsDB.getCardType(), cardsDB.getCardCategory(),
				cardsDB.getAccountName(), cardsDB.getIssuerName(), cardsDB.getSecureCode(),
				cardsDB.getExpirationMonth(), cardsDB.getExpirationYear(),
				cardsDB.getCardName(), cardsDB.getCardIdentificationNumber());
		cards.setId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cards", cards);
		//map.put("CD", cardsDB.getCD());
		//map.put("CB", cardsDB.getCB());

		return cards;
	}

	/*
	 * Get Card Information
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AbstractEntity edit(@PathVariable long id)
			throws HibernateException, Exception {

		logger.info("ID: " + id);

		Cards cardsDB = (Cards) dataServices.getEntity(id, Cards.class);

		if (cardsDB == null)
			throw new EntityNotFoundException();

		Cards cards = new Cards(null, null, cardsDB.getExpirationDate(),
				cardsDB.getCardType(), cardsDB.getCardCategory(),
				cardsDB.getAccountName(), cardsDB.getIssuerName(), null,
				cardsDB.getExpirationMonth(), cardsDB.getExpirationYear(),
				cardsDB.getCardName(), cardsDB.getCardIdentificationNumber());
		cards.setId(id);
		return cards;
	}

	/*
	 * Update Card Information
	 */
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.PUT, consumes =
	 * MediaType.APPLICATION_JSON_VALUE) public @ResponseBody Status
	 * updateCourses(@RequestBody Cards cards) throws HibernateException,
	 * Exception { if (cards == null) { throw new EmptyFormElementsException();
	 * }
	 * 
	 * CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
	 * .getAuthentication().getPrincipal(); Accounts accounts = new Accounts();
	 * accounts.setId(customUser.getId()); cards.setAccounts(accounts); try {
	 * cards.setUD(new Date()); cards.setUB(customUser.getId()); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * cards.setCardNumber(BCrypt.hashpw(cards.getCardNumber(),
	 * BCrypt.gensalt(12)));
	 * cards.setSecureCode(BCrypt.hashpw(cards.getSecureCode(),
	 * BCrypt.gensalt(12)));
	 * cards.setAccountName(BCrypt.hashpw(cards.getAccountName(),
	 * BCrypt.gensalt(12)));
	 * 
	 * try { SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 * if (cards.getCdString() != null)
	 * cards.setCD(dateFormat.parse(cards.getCdString())); } catch (Exception e)
	 * { e.printStackTrace(); } dataServices.updateEntity(cards);
	 * 
	 * return new Status(HttpStatus.OK.toString(),
	 * env.getProperty("cards.update")); }
	 */

	/*
	 * Delete Card Information
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, Cards.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("cards.deleted"));

	}
}
