package com.platform.registration.controller

import com.platform.registration.constants.RegistrationConstants
import com.platform.registration.dto.UserSignInRequest
import com.platform.registration.dto.UserSignUpRequest
import com.platform.registration.model.User
import com.platform.registration.service.RegistrationService
import com.platform.registration.error.ServiceException
import com.platform.registration.service.imp.RegistrationServiceImpl
import com.platform.registration.utils.JwtTokenUtil;
import org.springframework.http.HttpStatus
import spock.lang.Specification

class RegistrationControllerSpec extends Specification{

    def "proper requests for signup api, should call signUpUser and return 200"() {
        given:
        def userSignUpRequest = new UserSignUpRequest().tap{
            it.password = "sample"
            it.userName = "sample"
            it.phoneNumber = "659892323"
            it.firstName = "user first name"
            it.lastName = "user last name"
            it.emailId = "user@gmail.com"
        }
        def registrationService = Mock(RegistrationService)
        def registrationValidator = Mock(RegistrationValidator)
        def controller = new RegistrationController().tap {
            it.registrationService = registrationService
            it.registrationValidator = registrationValidator
        }

        when:
        def response = controller.registerUser(userSignUpRequest)

        then:
        1 * registrationService.signUpUser(_)
        response.statusCodeValue == 200
    }

    def "invalid requests for signup api, should return 400"() {
        given:
        def userSignUpRequest = new UserSignUpRequest().tap{
            it.password = "sample"
        }
        def registrationService = Mock(RegistrationService)
        def controller = new RegistrationController().tap {
            it.registrationService = registrationService
            it.registrationValidator = new RegistrationValidator()
        }

        when:
        def response = controller.registerUser(userSignUpRequest)

        then:
        def exception = thrown(ServiceException)
        exception.status == HttpStatus.BAD_REQUEST
    }

    def "Error at signup service, should return 500"() {
        given:
        def userSignUpRequest = new UserSignUpRequest().tap{
            it.password = "sample"
        }
        def registrationService = Mock(RegistrationService)
        def registrationValidator = Mock(RegistrationValidator)
        def controller = new RegistrationController().tap {
            it.registrationService = registrationService
            it.registrationValidator = registrationValidator
        }

        and:
        controller.registrationService.signUpUser(_) >> {throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, RegistrationConstants.PROCESSING_ERROR, RegistrationConstants.PROCESSING_ERROR_DETAILS)}

        when:
        def response = controller.registerUser(userSignUpRequest)

        then:
        def exception = thrown(ServiceException)
        exception.status == HttpStatus.INTERNAL_SERVER_ERROR
    }

//    def "proper requests for sign in api, should call signIn User and return 200"() {
//        given:
//        def userSignInRequest = new UserSignInRequest().tap{
//            it.password = "sample"
//            it.userName = "sample"
//        }
//        def user = new User(
//            "sample",
//            "sample",
//            "659892323",
//            "user first name",
//            "user last name",
//            "user@gmail.com").tap {
//            it.id = "1234"
//        }
//        def registrationService = Mock(RegistrationServiceImpl)
//        def registrationValidator = Mock(RegistrationValidator)
//        def controller = new RegistrationController().tap {
//            it.registrationService = registrationService
//            it.registrationValidator = registrationValidator
//            it.jwtTokenUtil = Mock(JwtTokenUtil)
//            it.signInUser().tap {
//                it.user = user
//            }
//        }
//
//        and:
//        controller.registrationService.signInUser(_) >> user
//        controller.jwtTokenUtil.generateToken(_) >> "token"
//
//        when:
//        def response = controller.signInUser(userSignInRequest)
//
//        then:
////        1 * registrationService.signInUser(_)
//        response.statusCodeValue == 200
//    }

    def "invalid requests for sign in api, should return 400"() {
        given:
        def userSignInRequest = new UserSignInRequest().tap{
            it.password = "sample"
        }
        def registrationService = Mock(RegistrationService)
        def controller = new RegistrationController().tap {
            it.registrationService = registrationService
            it.registrationValidator = new RegistrationValidator()
        }

        when:
        def response = controller.signInUser(userSignInRequest)

        then:
        def exception = thrown(ServiceException)
        exception.status == HttpStatus.BAD_REQUEST
    }

    def "Error at signIn service, should return 500"() {
        given:
        def userSignInRequest = new UserSignInRequest().tap{
            it.password = "sample"
        }
        def registrationService = Mock(RegistrationService)
        def registrationValidator = Mock(RegistrationValidator)
        def controller = new RegistrationController().tap {
            it.registrationService = registrationService
            it.registrationValidator = registrationValidator
        }

        and:
        controller.registrationService.signInUser(_) >> {throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, RegistrationConstants.PROCESSING_ERROR, RegistrationConstants.PROCESSING_ERROR_DETAILS)}

        when:
        def response = controller.signInUser(userSignInRequest)

        then:
        def exception = thrown(ServiceException)
        exception.status == HttpStatus.INTERNAL_SERVER_ERROR
    }
}
