package ua.kiss.trafficlights.fragments

object InterfaceHolder {
    private var openDialog: OpenDialog? = null

    fun set(openDialog: OpenDialog) {
        this.openDialog = openDialog
    }

    fun get() = openDialog
}
