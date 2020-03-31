package app.spidy.kotlinutils


// TODO: not implemented yet
class Promise(val callback: (resolve: (Any?) -> Unit, reject: (Any?) -> Unit) -> Unit) {
    private var ret: Any? = null
    init {
        callback({
            ret = it
        }, {
            catch {

            }
        })
    }

    fun then(thenCallback: (Any?) -> Any?) {

    }

    fun catch(catchCallback: (Any?) -> Any?) {

    }
}
