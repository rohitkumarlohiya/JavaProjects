package com.estel.controller;

import com.estel.entity.City;
import com.estel.entity.State;
import com.estel.service.CityService;
import com.estel.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;
import java.util.Map;

@Controller
public class AjaxController {

    @Autowired
    CityService cityService;

    @Autowired
    StateService stateService;


    public static String replaceSpecialCharacter(String aTagFragment) {
        final StringBuffer result = new StringBuffer();
        final StringCharacterIterator iterator = new StringCharacterIterator(aTagFragment);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            if (character == '<') {
                result.append("&lt;");
            } else if (character == '>') {
                result.append("&gt;");
            } else if (character == '\"') {
                result.append("&quot;");
            } else if (character == '\'') {
                result.append("&#039;");
            } else if (character == '\\') {
                result.append("&#092;");
            } else if (character == '&') {
                result.append("&amp;");
            } else {
//the char is not a special one
//add it to the result as is
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

	@RequestMapping(value = "/getStateList/{stateId}", method = RequestMethod.GET)
	public @ResponseBody
	void getStateList(HttpServletRequest request, HttpServletResponse response,
                              Map<String, Object> map, @PathVariable("stateId") Long stateId) throws Exception {

        String result = "";
        List<City> cityList = null;

        if (stateId != null) {

            cityList = cityService.listAllActiveCityByStateId(stateId);

            for (City city : cityList) {

                result = result + replaceSpecialCharacter(city.getCityDescription()) + "|" + replaceSpecialCharacter(city.getCityDescription()) + "####";
            }
        }

        if (result.equalsIgnoreCase("")) {
            result = "0|N####";
        }

        //System.out.println("<result>" + result + "</result>");

        response.setContentType("text/xml");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "must-revalidate");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.getWriter().write("<result>" + result + "</result>");

	}

    @RequestMapping(value = "/getCountryList/{countryId}", method = RequestMethod.GET)
    public @ResponseBody
    void getCountryList(HttpServletRequest request, HttpServletResponse response,
                      Map<String, Object> map,@PathVariable("countryId") Long countryId) throws Exception {

        String result = "";
        List<State> stateList = null;

        if (countryId != null) {

            stateList = stateService.listAllActiveCityByStateId(countryId);

            for (State state : stateList) {

                result = result + replaceSpecialCharacter(state.getStateDescription()) + "|" + replaceSpecialCharacter(state.getStateDescription()) + "####";
            }
        }

        if (result.equalsIgnoreCase("")) {
            result = "0|N####";
        }

        //System.out.println("<result>" + result + "</result>");

        response.setContentType("text/xml");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "must-revalidate");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.getWriter().write("<result>" + result + "</result>");
    }
}
