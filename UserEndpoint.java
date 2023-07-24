package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.user.UserDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
* UserEndpoint is a class for sending REST requests to "/users" endpoint
*
* @author      Vasyl Boliuk
* @version     %I%, %G%
*/
public class UserEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USERS_END = "/users";
    private static final String USERS_RESOURCE_END = "/users/{userID}";

    /**
    * Creates an instance of UserEndpoint
    *
    * @param specification request specification for HTTP request
    */
    public UserEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
    * Sends POST request to "/users" endpoint,
    * creates a new user
    * then extracts response body as UserDto class instance
    *
    * @param userDto body of a request containing information about user
    * @return response body extracted into UserDto class instance
    */
    public UserDto create(UserDto userDto) {
        return create(userDto, HttpStatus.CREATED)
            .extract().as(UserDto.class);
    }

    /**
    * Sends POST request to "/users" endpoint,
    * creates a new user
    *
    * @param userDto body of a request containing information about user
    * @param status expected response status
    * @return REST Assured ValidatableResponse object
    */
    public ValidatableResponse create(UserDto userDto, HttpStatus status) {
        LOGGER.info("Create new User");
        return post(
            this.specification,
            USERS_END,
            userDto)
            .statusCode(status.getCode());
    }

    /**
    * Sends PUT request to "/users/{userID}" endpoint,
    * updates existing user by id
    * then extracts response body as UserDto class instance
    *
    * @param id id of a user to update
    * @param userDto body of a request containing information about user
    * @return response body extracted into UserDto class instance
    */
    public UserDto update(int id, UserDto userDto) {
        return update(userDto, id, HttpStatus.OK)
            .extract().as(UserDto.class);
    }

    /**
    * Sends PUT request to "/users/{userID}" endpoint,
    * updates existing user by id
    *
    * @param id id of a user to update
    * @param userDto body of a request containing information about user
    * @param status expected response status
    * @return REST Assured ValidatableResponse object
    */
    public ValidatableResponse update(UserDto userDto, int id, HttpStatus status) {
        LOGGER.info("Update User by id [{}]", id);
        return put(
            this.specification,
            USERS_RESOURCE_END,
            userDto,
            id)
            .statusCode(status.getCode());
    }

    /**
    * Sends GET request to "/users/{userID}" endpoint,
    * gets an existing user by id
    * then extracts response body as UserDto class instance
    *
    * @param id id of a user to get
    * @return response body extracted into UserDto class instance
    */
    public UserDto getById(String id) {
        return getById(id, HttpStatus.OK)
            .extract().as(UserDto.class);
    }

    /**
    * Sends GET request to "/users/{userID}" endpoint,
    * gets an existing user by id
    *
    * @param id id of a user to get
    * @param status expected response status
    * @return REST Assured ValidatableResponse object
    */
    public ValidatableResponse getById(String id, HttpStatus status) {
        LOGGER.info("Get User by id [{}]", id);
        return get(
            this.specification,
            USERS_RESOURCE_END,
            id)
            .statusCode(status.getCode());
    }

    /**
    * Sends GET request to "/users" endpoint,
    * gets all existing users
    * then extracts response body as a list of UserDto class instances
    *
    * @return response body extracted into a list of UserDto class instances
    */
    public List<UserDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(UserDto[].class));
    }

    /**
    * Sends GET request to "/users" endpoint,
    * gets all existing users
    *
    * @param status expected response status
    * @return REST Assured ValidatableResponse object
    */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Users");
        ValidatableResponse response = get(this.specification, USERS_END);
        response.statusCode(status.getCode());
        return response;
    }

}
