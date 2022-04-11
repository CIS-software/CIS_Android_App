package first.android.cis.domain.usecases.signInUp

import first.android.cis.domain.models.user.UserSignInfo
import first.android.cis.domain.repository.UserRepository

class CreateUser(private val userRepository: UserRepository) {
    fun createUser(userSignInfo: UserSignInfo){
       // userRepository.createUserService(userSignInfo)
    }
}