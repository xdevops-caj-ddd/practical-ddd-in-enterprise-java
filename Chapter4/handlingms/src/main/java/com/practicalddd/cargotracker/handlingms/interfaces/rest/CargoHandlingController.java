package com.practicalddd.cargotracker.handlingms.interfaces.rest;


import com.practicalddd.cargotracker.handlingms.application.internal.commandservices.HandlingActivityRegistrationCommandService;
import com.practicalddd.cargotracker.handlingms.interfaces.rest.dto.HandlingActivityRegistrationResource;
import com.practicalddd.cargotracker.handlingms.interfaces.rest.transform.HandlingActivityRegistrationCommandDTOAssembler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Controller for the REST API
 */
@Path("/cargohandling")
@ApplicationScoped
public  class CargoHandlingController {


    private HandlingActivityRegistrationCommandService handlingActivityRegistrationCommandService; // Application Service Dependency

    /**
     * Provide the dependencies
     * @param handlingActivityRegistrationCommandService
     */
    @Inject
    public CargoHandlingController(HandlingActivityRegistrationCommandService handlingActivityRegistrationCommandService){
        this.handlingActivityRegistrationCommandService = handlingActivityRegistrationCommandService;
    }

    /**
     * POST method to register Handling Activities
     * @param handlingActivityRegistrationResource
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerHandlingActivity(HandlingActivityRegistrationResource handlingActivityRegistrationResource){
        System.out.println("***"+handlingActivityRegistrationResource.getBookingId());
        System.out.println("***"+handlingActivityRegistrationResource.getHandlingType());

        handlingActivityRegistrationCommandService.registerHandlingActivityService(HandlingActivityRegistrationCommandDTOAssembler.toCommandFromDTO(handlingActivityRegistrationResource));
        final Response returnValue = Response.ok()
                .entity("Handling Activity Registered")
                .build();
        return returnValue;
    }
}
