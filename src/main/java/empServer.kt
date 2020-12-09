fun main() {
    val controller: Controller
    controller = DaggerController.builder().build()
    controller.getServer()
}
