#### Error support in `Result` api

List of supported `errorType`'s of `FacebookError`
```scala
import com.github.vooolll.domain.oauth._
import com.github.vooolll.domain.oauth.FacebookError._

Set(InvalidApiKey, Session, Unknown, ServiceDown, TooManyCalls, UserTooManyCalls, PermissionDenied,
  AccessTokenHasExpired, ApplicationLimitReached, Blocked, DuplicatePost,
  ErrorPostingLink, PermissionNotGrantedOrRemoved, InvalidVerificationCodeFormat, SpecifiedObjectNotFound)
```