# Flow
- Kotlin의 Flow는 순차적으로 값을 내보내고 정상적으로 또는 예외로 완료되는 비동기적인 데이터 스트림.
- 여러개의 값을 반환하는 function을 만들때 사용하는 coroutine builder.
- Flow는 Cold Stream이기 때문에 값을 방출할 때마다 사용된다.
- 매번 새로운 Flow를 생성하고, 소모성이 아니다.
- flow로 만들어진 collection은 이를 호출한 caller의 coroutine context에서 수행되며, 이를 context preservation이라고 부른다.
- 하지만 CPU를 많이 사용하는 flow 연산이라면, background Thread에서 수행햐야하고, 이 결과를 받아 UI를 업데이트 하는 작업은 main Thread에서 처리하도록 해야 하는 경우가 발생할 수 있음.
- 보통의 Coroutine에서는 withContext를 이용하여 쉽게 전환할 수 있다.
- 하지만 위에서 언급한 Context Preservation 속성으로 인하며 emit을 하는 context와 수신하는 context를 다르지 못하도록 설계되어있다.
- 이럴 경우 flowOn Operator를 이용하여 emission하는 부분의 context를 바꿔줄 수 있다.
- 하지만 Coroutine을 수행하는 주체는 달라지며 각각의 다른 Coroutine에서 실행된다. (Thread가 다름.)


## Catch
- emttier의 코드가 자신에 의해서 발생한 exception의 handling을 캡슐화 하기 위한 방안.
- catch의 body 내부에 emit을 이용하여 값을 emission 할 수 있음.
```
fun TEST12(): Flow<String> = flow{
    for(i in 1..3){
        Log.d(MainActivity.TEST12, "[EMIT] $i")
        emit(i)
    }
}.map { value ->
    check(value <=1) { Log.d(MainActivity.TEST12, "[EXCEPTION] Crash on ::$value")}
    "Result $value"
}

fun runOnTest12() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST12, "[START]")
    val time = measureTimeMillis {
        TEST12()
            .catch { e -> emit("[EMIT_CRASH] ::$e") }
            .collect{
                Log.d(MainActivity.TEST12, "[COLLECT] Collect:: $it")
            }

    }
    Log.d(MainActivity.TEST11, "[END] ** Collected in $time ms ** ")
}
```
```
D/TEST12: [START]
D/TEST12: [EMIT] 1
D/TEST12: [COLLECT] Collect:: Result 1
D/TEST12: [EMIT] 2
D/TEST12: [EXCEPTION] Crash on ::2
D/TEST12: [COLLECT] Collect:: [EMIT_CRASH] ::java.lang.IllegalStateException: 33
```

## Combine
- Flow가 conflation처럼 최신값이나 최종 연산값만을 사용하는 형태라면, 현재 flow에서 최신값만을 기준으로 연산하는 작업을 수행하도록 함.
- 즉 두개의 flow에서 값을 emit하고 서로 다른 타이밍으로 방출 될 때.최신값만을 기준으로 두개의 방출값은 연산하도록 함.
```
fun integerEmit() = (1..3).asFlow().onEach { delay(300) }
fun stringEmit() = flowOf("one","two","three").onEach { delay(400) }

fun runOnTest13() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST13, "[START]")
    val time = measureTimeMillis {
        integerEmit().combine(stringEmit()){ a,b -> "$a -> $b"}
            .collect { value ->
                Log.d(MainActivity.TEST13, "[COLLECT] Collect::$value")
            }

    }
    Log.d(MainActivity.TEST13, "[END] ** Collected in $time ms ** ")
}
```
```
D/TEST13: [START]
D/TEST13: [COLLECT] Collect::1 -> one
D/TEST13: [COLLECT] Collect::2 -> one
D/TEST13: [COLLECT] Collect::2 -> two
D/TEST13: [COLLECT] Collect::3 -> two
D/TEST13: [COLLECT] Collect::3 -> three
D/TEST13: [END] ** Collected in 1334 ms ** 
```
### Ect.
- distinctUntilChanged(): 데이터가 실제로 변화가 있을 경우에만 방출한다.
# Reference
- [https://tourspace.tistory.com/260?category=797357](https://tourspace.tistory.com/260?category=797357)