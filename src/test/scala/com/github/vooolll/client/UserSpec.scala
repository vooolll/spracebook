package com.github.vooolll.client

import java.net.URL

import com.github.vooolll.base._
import feed._
import cats.implicits._
import com.github.vooolll.base.TestConfiguration._
import com.github.vooolll.domain.oauth.FacebookError
import com.github.vooolll.domain.profile.FacebookUserAttribute.defaultAttributeValues
import com.github.vooolll.domain.profile._

class UserSpec extends FacebookClientSupport {
  val realUserId = FacebookUserId("117656352360395")

  val realUser = FacebookUser(
    id = realUserId,
    email = Some("potnyiakk@gmail.com"),
    name = Some("Bob Willins"),
    picture = Some(FacebookUserPicture(50.0, isSilhouette = false, new URL("https://lookaside.facebook.com/platform/profilepic/"), 50.0)),
    firstName = Some("Bob"),
    lastName = Some("Willins"),
    link = Some(new URL("https://www.facebook.com")),
    gender = Gender.Female.some,
    ageRange = AgeRange(21,None).some,
    hometown = Some(FacebookTown("115486911798138", "Almaty, Kazakhstan")),
    location = Some(FacebookTown("115353315143936", "Rome, Italy")))

  "Facebook Graph Api" should {
    "return user profile" in { c =>
      c.userProfile(realUserId, userTokenRaw, defaultAttributeValues) map (_.withoutQueryParams shouldBe realUser)
    }

    "return user profile result" in { c =>
      c.userProfileResult(realUserId, userTokenRaw) map (_.map(_.withoutQueryParams) shouldBe realUser.asRight)
    }

    "return error SpecifiedObjectNotFound" in { c =>
      c.userProfileResult(realUserId.copy(value = "asd"), userTokenRaw) map {
        case Right(_) => fail("bad request expected")
        case Left(e)  => e.errorType shouldBe FacebookError.SpecifiedObjectNotFound
      }
    }

    "return error InvalidVerificationCodeFormat" in { c =>
      c.userProfileResult(realUserId.copy(value = "777661112359912"), userTokenRaw) map {
        case Right(_) => fail("bad request expected")
        case Left(e)  => e.errorType shouldBe FacebookError.InvalidVerificationCodeFormat
      }
    }
  }
}