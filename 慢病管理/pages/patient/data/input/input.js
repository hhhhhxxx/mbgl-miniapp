import constant from "../../../../utils/constant";
import message from "../../../../utils/message";
import patientApi from "../../../../api/patientApi";
import storage from "../../../../utils/storage";
import key from "../../../../utils/key";

Page({
    data: {
        field: null,

        form: {
            heartRate: '',
            bloodSugar: '',
            bloodPressure1: '',
            bloodPressure2: '',
        },

        // rules: [],
        rules: [{
            name: 'heartRate',
            rules: {required: true, message: '请输入心率'},
        }, {
            name: 'bloodSugar',
            rules: {required: true, message: '请输入血糖'},
        }, {
            name: 'bloodPressure1',
            rules: [{required: true, message: '请输入收缩压'}, {range: [80, 150], message: '数值超出范围'},]
        }, {
            name: 'bloodPressure2',
            rules: [{required: true, message: '请输入舒张压'}, {range: [50, 100], message: '数值超出范围'},
            ]
        }]
    },
    onLoad: function (options) {
        const user = storage.get(key.USER)
        this.setData({
            field: options.field
        })
    },

    formInputChange(e) {
        const {field} = e.currentTarget.dataset
        this.setData({
            [`form.${field}`]: e.detail.value
        })
    },

    submitForm() {

        if (this.data.field !== 'bloodPressure') {

            console.log(3)
            this.selectComponent('#form').validateField(this.data.field, (valid, errors) => {
                console.log('valid', valid, errors)
                if (!valid) {
                    message.error(errors.message)
                } else {
                    console.log(this.data.form)
                }
            })

        } else {

            console.log(4)

            this.selectComponent('#form').validateField(this.data.field + '1', (valid, errors) => {
                console.log('valid', valid, errors)
                if (!valid) {
                    message.error(errors.message)
                } else {

                    this.selectComponent('#form').validateField(this.data.field + '2', (valid, errors) => {

                        if (!valid) {
                            message.error(errors.message)
                        } else {
                            console.log('两个通过')
                        }


                    })

                    console.log(this.data.form)

                }
            })
        }
    }
});
