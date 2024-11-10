package com.effective.kotlin.chapter01.item08

/** 1. nullable 타입 처리
 * - ?. , 스마트 캐스팅, Evis 연산자 등을 활용해서 안전하게 처리한다.
 * - 오류를 throw 한다.
 * - 함수 또는 프로퍼티를 리팩터링해서 nullable 타입이 나오지 않게 바꾼다.
 */
class Printer(
    val name: String? = null,
) {
    fun print() {

    }
}

fun getPrinter(): Printer? {
    val temp = true
    if (temp) {
        return null
    }
    return Printer()
}

fun example01() {
    val printer: Printer? = getPrinter()
//    printer.print()                         // 컴파일 오류
    printer?.print()                        // 안전 호출
    if (printer != null) printer.print()    // 스마트 캐스팅
    printer!!.print()                       // not-null assertion
}

/** 2. Elvis 연산자는 오른쪽에 return 또는 throw을 포함한 모든 표현식이 허용된다.
 */
fun example02() {
    val printer: Printer? = getPrinter()

    val printerName1 = printer?.name ?: "Unnamed"
    val printerName2 = printer?.name ?: return
    val printerName3 = printer?.name ?: throw Error("Printer must be name")
}

/** 3. 오류 throw 하기
 */
fun process(user: User) {
    requireNotNull(user.name)
    val context = checkNotNull(context)
    val networkService = getNetworkService(context) ?: throw NoInternetConnection()
    networkService.getData {  data, userData ->
        show(data!!, userData!!)
    }
}

class User(
    val name: String? = null,
)

val context = "default context"

fun getNetworkService(context: String): NetworkService? {
    return NetworkService()
}

fun show(data: String, userData: String) {
    println("Data: $data, UserData: $userData")
}

class NetworkService {
    fun getData(callback: (data: String?, userData: String?) -> Unit) {
        callback("Data from network", "UserData from network")
    }
}

class NoInternetConnection() : Throwable()


/** 4. not null assertion과 관련된 문제
 */

/** 5. 의미 없는 nullability 피하기
 */

/** 6. lateinit 프로퍼티와 notNull 델리 게이트
 */