// pages/data/equipment/equipment.js
Page({

    data: {
        info: "",
        connectedDeviceId: "",
        devices: [

        ],
    },

    // that: this,

    lanyatest1 (event) {
        var that = this
        return new Promise((resolve, reject) => {
            wx.openBluetoothAdapter({
                success: function (res) {
                    resolve(res)
                },
                fail: function (res) {
                    that.setData({
                        info: '请打开蓝牙和定位功能'
                    })
                    reject(res)
                }
            })
        })
    },

    lanyatest2 (event) {
        var that = this
        return new Promise((resolve, reject) => {
            wx.getBluetoothAdapterState({
                success: function (res) {
                    resolve(res)
                },
                fail: function (res) {
                    that.setData({
                        info: '蓝牙不可用'
                    })
                    reject(res)
                }
            })
        })
    },

    lanyatest3 (event) {
        var that = this
        return new Promise((resolve, reject) => {
            wx.startBluetoothDevicesDiscovery({
                success: function (res) {
                    resolve(res)
                }
            })
        })
    },

    lanyatest4 (event) {
        var that = this
        return new Promise((resolve, reject) => {
            wx.getBluetoothDevices({
                success: function (res) {
                    that.setData({
                        devices: res.devices
                    })
                    resolve(res)
                }
            })
        })

    },

    lanyaconnect (event) {
        wx.showLoading({
            title: '加载中',
          })
        var that = this
        return new Promise((resolve, reject) => {
            wx.createBLEConnection({
                deviceId: event.currentTarget.id,
                success: function (res) {
                    let list = that.data.devices

                    list.forEach((v, i) => {
                        if (v.deviceId == event.currentTarget.id) {
                            list[i].active = true
                        }
                    });

                    that.setData({
                        info: "连接成功",
                        devices: list
                    })
                    wx.hideLoading()
                    resolve(res)
                },
                fail: function () {
                    that.setData({
                        info: "连接失败"
                    })
                    wx.hideLoading()
                    reject(res)
                },
            })
        })
    },

    cancelConnect (event) {
        wx.showLoading({
            title: '加载中',
          })
        var that = this;
        return new Promise((resolve, reject) => {
            wx.closeBLEConnection({
                deviceId: event.currentTarget.id,
                success: function (res) {
                    let list = that.data.devices

                    list.forEach((v, i) => {
                        if (v.deviceId == event.currentTarget.id) {
                            list[i].active = false
                        }
                    });

                    that.setData({
                        // info: "连接成功",
                        devices: list
                    })
                    wx.hideLoading()
                    resolve(res)
                },
                fail: function (res) {
                    wx.hideLoading()
                    reject(res)
                }
            })
        })
    },

    stopSearch (event) {
        var that = this
        return new Promise((resolve, reject) => {
            wx.stopBluetoothDevicesDiscovery({
                success: function (res) {
                    resolve(res)
                }
            })
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var here = this    
        here.lanyatest1().then((res) => {
            wx.showLoading({
                title: '蓝牙设备搜索中',
            })
            here.lanyatest2().then((res) => {
                // console.log(2);
                here.lanyatest3().then((res) => {
                    // console.log(3);
                    here.lanyatest4().then((res) => {
                        wx.hideLoading()
                    })
                })
            })
        })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {
    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {
        wx.stopBluetoothDevicesDiscovery({
            success: function (res) {
                console.log("关闭蓝牙");
            }
        })
    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {
        this.lanyatest4().then((res) => {
            wx.stopPullDownRefresh()
        }).catch((res) => {
            that.setData({
                info: "获取失败"
            })
            wx.stopPullDownRefresh()
        })
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    }
})