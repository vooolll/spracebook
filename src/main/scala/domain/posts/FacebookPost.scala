package domain.posts

import java.time.Instant

import domain.profile.FacebookProfileId

final case class FacebookPostId(value: String)

final case class FacebookPost(
  id          : FacebookPostId,
  story       : Option[String],
  createdTime : Option[Instant],
  objectId    : Option[String],
  picture     : Option[String],
  from        : Option[FacebookProfileId])
