package com.mujur.e_lumapp.TES

interface GeneralView {
    /**
     * untuk menampilkan loading ketika awal memanggil API
     */
    fun showLoading()

    /**
     * akan dieksekusi ketika terjadi error sewaktu pemanggilan API
     */
    fun error(error: Throwable?)

    /**
     * dieksekusi ketika hasil dari API didapatkan
     */
    fun success(response: Any)

    /**
     * dieksekusi ketika selesai error atau sukses
     */
    fun hideLoading()
}