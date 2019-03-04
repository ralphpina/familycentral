package net.ralphpina.familycentral.family

import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

data class Family(val members: List<Member>)

data class Member(
    val firstName: String,
    val lastName: String,
    val dob: Long,
    val photo: String? = null
) {
    fun fullName() = "$firstName $lastName"

    fun dobAsString(): String = dob.toString()
}

interface FamilyRepository {
    fun observe(): Observable<Family>
}

class FamilyRepositoryImpl @Inject constructor() : FamilyRepository {
    override fun observe(): Observable<Family> {
        val dad = Member("Ralph", "Pina", Date().time)
        val mom = Member("Johanna", "Pina", Date().time)
        val daughter = Member("Natalie", "Pina", Date().time)
        val son = Member("Max", "Pina", Date().time)
        return Observable.just(Family(listOf(dad, mom, daughter, son)))
    }
}