package com.platform.registration.controller

import com.platform.registration.constants.RegistrationConstants
import com.platform.registration.dto.UserSignInRequest
import com.platform.registration.dto.UserSignUpRequest
import com.platform.registration.error.ServiceException
import com.platform.registration.service.RegistrationService
import org.springframework.http.HttpStatus
import spock.lang.Specification

class UserControllerSpec extends Specification{

    def "proper requests for get user api, should call getUserDetails and return 200"() {
        given:
        def registrationService = Mock(RegistrationService)
        def registrationValidator = Mock(RegistrationValidator)
        def controller = new UserController().tap {
            it.registrationService = registrationService
            it.registrationValidator = registrationValidator
        }

        when:
        def response = controller.getUserDetails("sampleId")

        then:
        1 * registrationService.getUserDetails(_)
        response.statusCodeValue == 200
    }

    def "Error at getUserDetails service, should return 500"() {
        given:
        def userSignUpRequest = new UserSignUpRequest().tap{
            it.password = "sample"
        }
        def registrationService = Mock(RegistrationService)
        def registrationValidator = Mock(RegistrationValidator)
        def controller = new UserController().tap {
            it.registrationService = registrationService
            it.registrationValidator = registrationValidator
        }

        and:
        controller.registrationService.getUserDetails(_) >> {throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, RegistrationConstants.PROCESSING_ERROR, RegistrationConstants.PROCESSING_ERROR_DETAILS)}

        when:
        def response = controller.getUserDetails("sampleId")

        then:
        def exception = thrown(ServiceException)
        exception.status == HttpStatus.INTERNAL_SERVER_ERROR
    }

    def "proper requests for get user by email id api, should call getUserByEmailId and return 200"() {
        given:
        def registrationService = Mock(RegistrationService)
        def registrationValidator = Mock(RegistrationValidator)
        def controller = new UserController().tap {
            it.registrationService = registrationService
            it.registrationValidator = registrationValidator
        }

        when:
        def response = controller.searchUserByEmailId("emailId")

        then:
        1 * registrationService.getUserByEmailId(_)
        response.statusCodeValue == 200
    }

    def "Error at getUserByEmailId service, should return 500"() {
        given:
        def userSignUpRequest = new UserSignUpRequest().tap{
            it.password = "sample"
        }
        def registrationService = Mock(RegistrationService)
        def registrationValidator = Mock(RegistrationValidator)
        def controller = new UserController().tap {
            it.registrationService = registrationService
            it.registrationValidator = registrationValidator
        }

        and:
        controller.registrationService.getUserByEmailId(_) >> {throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, RegistrationConstants.PROCESSING_ERROR, RegistrationConstants.PROCESSING_ERROR_DETAILS)}

        when:
        def response = controller.searchUserByEmailId("sampleId")

        then:
        def exception = thrown(ServiceException)
        exception.status == HttpStatus.INTERNAL_SERVER_ERROR
    }
}
