// pages/data/equipment/equipment.js
Page({

    data: {
        info: "",
        connectedDeviceId: "",
        devices: [],
    },

    // 打开蓝牙
    lanyatest1(event) {
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

    // 获取蓝牙状态
    lanyatest2(event) {
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

    // 发现设备
    lanyatest3(event) {
        var that = this
        return new Promise((resolve, reject) => {
            wx.startBluetoothDevicesDiscovery({
                success: function (res) {
                    resolve(res)
                }
            })
        })
    },

    // 获取设备
    lanyatest4(event) {
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

    // 创建连接
    lanyaconnect(event) {
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
                        devices: list,
                        connectedDeviceId: event.currentTarget.id
                    })

                    that.getLanyaService().then(res => {

                        // var deviceId = that.data.connectedDeviceId
                        //
                        // var serviceId = '0000180D-0000-1000-8000-00805F9B34FB'
                        // var uuid = '00002A37-0000-1000-8000-00805F9B34FB'
                        //
                        // wx.notifyBLECharacteristicValueChange({
                        //     deviceId,
                        //     serviceId,
                        //     characteristicId: uuid,
                        //     state: true,
                        // })

                        var deviceId = that.data.connectedDeviceId
                        var serviceId = '0000FEE1-0000-1000-8000-00805F9B34FB'
                        var uuid = '00000009-0000-3512-2118-0009AF100700'

                        console.log('开始监听0009')

                        wx.notifyBLECharacteristicValueChange({
                            deviceId,
                            serviceId,
                            characteristicId: uuid,
                            state: true,
                        })

                        let buffer = new ArrayBuffer(18)
                        let dataView = new DataView(buffer)

                        dataView.setUint8(0, 0x01)
                        dataView.setUint8(1, 0x08)
                        dataView.setUint8(2, 0x30)
                        dataView.setUint8(3, 0x31)
                        dataView.setUint8(4, 0x32)
                        dataView.setUint8(5, 0x33)
                        dataView.setUint8(6, 0x34)
                        dataView.setUint8(7, 0x35)
                        dataView.setUint8(8, 0x36)
                        dataView.setUint8(9, 0x37)
                        dataView.setUint8(10, 0x38)
                        dataView.setUint8(11, 0x39)
                        dataView.setUint8(12, 0x40)
                        dataView.setUint8(13, 0x41)
                        dataView.setUint8(14, 0x42)
                        dataView.setUint8(15, 0x43)
                        dataView.setUint8(16, 0x44)
                        dataView.setUint8(17, 0x45)

                        console.log(buffer)

                        wx.writeBLECharacteristicValue({
                            deviceId,
                            serviceId,
                            characteristicId: uuid,
                            value: buffer,
                        })


                    })


                    wx.hideLoading()
                    resolve(res)
                },
                fail: function (res) {
                    that.setData({
                        info: "连接失败"
                    })
                    wx.hideLoading()
                    reject(res)
                },
            })
        })
    },

    // 获取服务
    getLanyaService() {

        var that = this

        var deviceId = that.data.connectedDeviceId

        return new Promise((resolve, reject) => {
            wx.getBLEDeviceServices({
                deviceId, // 搜索到设备的 deviceId
                success: (res) => {
                    for (let i = 0; i < res.services.length; i++) {
                        // if (res.services[i].isPrimary) {
                        //     // 可根据具体业务需要，选择一个主服务进行通信
                        // }
                        console.log(i, res.services[i])

                        let serviceId = res.services[i].uuid

                        // 获取服务里面的特征值
                        wx.getBLEDeviceCharacteristics({
                            deviceId, // 搜索到设备的 deviceId
                            serviceId, // 上一步中找到的某个服务
                            success: (res) => {
                                // for (let i = 0; i < res.characteristics.length; i++) {
                                //     let item = res.characteristics[i]
                                //     console.log(item)
                                // }
                                // console.log("---------------")
                                resolve(res)
                            }
                        })
                    }
                },
                fail: function (res) {
                    that.setData({
                        info: "获取服务失败"
                    })
                    reject(res)
                },
            })
        })
    },

    // 取消连接
    cancelConnect(event) {
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

    // 停止搜索
    stopSearch(event) {
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

    zhendong() {
        // 振动
        var deviceId = that.data.connectedDeviceId
        var serviceId = '00001802-0000-1000-8000-00805F9B34FB'
        var characteristicId = '00002A06-0000-1000-8000-00805F9B34FB'

        // 本示例是向蓝牙设备发送一个 0x00 的 16 进制数据
        // 实际使用时，应根据具体设备协议发送数据
        let buffer = new ArrayBuffer(1)
        let dataView = new DataView(buffer)
        dataView.setUint8(0, 0x01)
        wx.writeBLECharacteristicValue({
            deviceId,
            serviceId,
            characteristicId,
            value: buffer,
        })
    },

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

        wx.onBLECharacteristicValueChange((result) => {
            console.log('监听：', result)


            if (result.characteristicId === "00000009-0000-3512-2118-0009AF100700") {

                var value = new Uint8Array(result.value);

                if (value[0] == 0x10 && value[1] == 0x01 && value[2] == 0x01) {



                    let buffer = new ArrayBuffer(18)
                    let dataView = new DataView(buffer)

                    dataView.setUint8(0, 0x02)
                    dataView.setUint8(1, 0x08)

                    wx.writeBLECharacteristicValue({
                        deviceId: this.data.connectedDeviceId,
                        serviceId: result.serviceId,
                        characteristicId: result.characteristicId,
                        value: buffer,
                    })

                } else if (value[0] == 0x10 && value[1] == 0x02 && value[2] == 0x01) {

                    console.log('way2')
                } else {
                    console.log('way3')
                }
            } else{
                console.log('none thing')
            }
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

        const that = this

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
