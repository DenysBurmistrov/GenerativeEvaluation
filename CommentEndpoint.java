package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.comment.CommentDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
* CommentEndpoint is a class for sending REST requests to "/comments" endpoint
*
* @author      Vasyl Boliuk
* @version     %I%, %G%
*/
public class CommentEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMENTS_END = "/comments";
    private static final String COMMENTS_RESOURCE_END = "/comments/{commentID}";

    /**
    * Creates an instance of CommentEndpoint
    *
    * @param specification request specification for HTTP request
    */
    public CommentEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
    * Sends POST request to "/comments" endpoint,
    * creates a new comment
    * then extracts response body as CommentDto class instance
    *
    * @param commentDto body of a request containing information about comment
    * @return response body extracted into CommentDto class instance
    */
    public CommentDto create(CommentDto commentDto) {
        return create(commentDto, HttpStatus.CREATED)
            .extract().as(CommentDto.class);
    }

    /**
    * Sends POST request to "/comments" endpoint,
    * creates a new comment
    *
    * @param commentDto body of a request containing information about comment
    * @param status expected response status
    * @return REST Assured ValidatableResponse object
    */
    public ValidatableResponse create(CommentDto commentDto, HttpStatus status) {
        LOGGER.info("Create new Comment");
        return post(
            this.specification,
            COMMENTS_END,
            commentDto)
            .statusCode(status.getCode());
    }

    /**
    * Sends PUT request to "/comments/{commentID}" endpoint,
    * updates an existing comment by id
    * then extracts response body as CommentDto class instance
    *
    * @param id id of a comment to update
    * @param commentDto body of a request containing information about comment
    * @return response body extracted into CommentDto class instance
    */
    public CommentDto update(int id, CommentDto commentDto) {
        return update(commentDto, id, HttpStatus.OK)
            .extract().as(CommentDto.class);
    }

    /**
    * Sends PUT request to "/comments/{commentID}" endpoint,
    * updates an existing comment by id
    *
    * @param commentDto body of a request containing updated information about
    * comment
    * @param id id of a comment to update
    * @param status expected response status
    * @return REST Assured ValidatableResponse object
    */
    public ValidatableResponse update(CommentDto commentDto, int id, HttpStatus status) {
        LOGGER.info("Update Comment by id [{}]", id);
        return put(
            this.specification,
            COMMENTS_RESOURCE_END,
            commentDto,
            id)
            .statusCode(status.getCode());
    }

    /**
    * Sends GET request to "/comments/{commentID}" endpoint,
    * returns a comment by id
    * then extracts response body as CommentDto class instance
    *
    * @param id id of a comment to get
    * @return response body extracted into CommentDto class instance
    */
    public CommentDto getById(int id) {
        return getById(id, HttpStatus.OK)
            .extract().as(CommentDto.class);
    }


    /**
    * Sends GET request to "/comments/{commentID}" endpoint,
    * returns a comment by id
    *
    * @param id id of a comment to get
    * @param status expected response status
    * @return REST Assured ValidatableResponse object
    */
    public ValidatableResponse getById(int id, HttpStatus status) {
        LOGGER.info("Get Comment by id [{}]", id);
        return get(
            this.specification,
            COMMENTS_RESOURCE_END,
            String.valueOf(id))
            .statusCode(status.getCode());
    }

    /**
    * Sends GET request to "/comments" endpoint,
    * gets all existing comments
    * then extracts response body as a list of CommentDto class instances
    *
    * @return response body extracted into list of CommentDto class instances
    */
    public List<CommentDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(CommentDto[].class));
    }

    /**
    * Sends GET request to "/comments" endpoint,
    * gets all existing comments
    *
    * @param status expected response status
    * @return REST Assured ValidatableResponse object
    */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Comments");
        ValidatableResponse response = get(this.specification, COMMENTS_END);
        response.statusCode(status.getCode());
        return response;
    }


}
