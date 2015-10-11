package com.ffmusic.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import com.ffmusic.backend.model.User;

import static com.ffmusic.backend.OfyService.ofy;


/**
 * Created by Andres Felipe Cruz on 03/10/2015.
 */

@Api(
        name = "ffMusicApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.ffmusic.com",
                ownerName = "backend.ffmusic.com",
                packagePath = ""
        )
)
public class UserEndPoint {

        /*
        * Searches an entity by ID.
        * @param id the User ID to search
        * @return the User associated to id
         */
        @ApiMethod(httpMethod = "GET")
        public final User getUserByEmail(@Named("email") final String email){
                return ofy().load().type(User.class).filter("email", email).first().now();

        }

        @ApiMethod(httpMethod = "POST")
        public User insertUser(final User userInstance){
                ofy().save().entity(userInstance).now();
                return userInstance;
        }


}
