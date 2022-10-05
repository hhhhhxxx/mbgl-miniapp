import {basePost, baseGet} from "../../../utils/request";
import wxApi from "../../../api/wxApi";
import storage from "../../../utils/storage";
import key from "../../../utils/key";

Component({
    properties: {},
    data: {
        nowStep: 0
    },

    lifetimes: {
        attached: function () {
            this.getStep()
        },
        detached: function () {
            // 在组件实例被从页面节点树移除时执行
        },
    },

    methods: {
        btn1() {
            wx.login({
                success(res) {
                    console.log('微信登录返回值', res)


                    wxApi.setSessionkey({
                        code: res.code
                    }).then(res => {
                        console.log(res)
                    })
                }
            })
        },

        getUserInfo: function () {
            var that = this
            wx.getUserProfile({
                desc: '系统测试', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
                success: (res) => {
                    console.log("获取用户信息成功", res)

                    let userInfo = {
                        avatarUrl: res.userInfo.avatarUrl,
                        nickName:res.userInfo.nickName
                    }

                    storage.set(key.WX_USER_INFO,userInfo)

                },
                fail: res => {
                    console.log("获取用户信息失败", res)
                }
            })
        },

        getStep() {
            // const that = this
            // wx.getWeRunData({
            //     success(res) {
            //         console.log('微信步数', res)
            //
            //         wxApi.getNowStep({
            //             encryptedData: res.encryptedData,
            //             iv: res.iv
            //         }).then(res => {
            //             console.log(res)
            //
            //             that.setData({
            //                 nowStep: res.response
            //             })
            //         })
            //     }
            // })
        }
    }
});
