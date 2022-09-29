// 公共url
import storage from "./storage";

const baseUrl = "http://localhost:8080"

const request = (url, params, header, method = 'get') => {

    return new Promise((resolve, reject) => {

        header['user_id'] = storage.getCurrentUserId()

        wx.request({
            ...params,
            url:  url,
            header: header,
            method: method,

            success: (res) => {
                if (res.data.code === 1) {
                    resolve(res.data)
                } else {
                    reject(res.data)
                }
            },
            fail: (err) => {
                reject(err)
            }
        });
    })
}

export const get = (url, params) => {

    let header = {
        // get请求的type
        "Content-Type": "application/json",
    }
    return request(baseUrl+url, { data: params }, header, "get")
}


export const post = (url, params) => {
    let header = {
        // post请求的type
        "Content-Type": "application/json",
    }

    return request(baseUrl+url, { data: params }, header, "post")
}

// 访问其他网站

export const baseGet = (url, params) => {

    let header = {
        // get请求的type
        "Content-Type": "application/json",
    }
    return request(url, { data: params }, header, "get")
}

export const basePost = (url, params) => {
    let header = {
        // post请求的type
        "Content-Type": "application/json",
    }

    return request(url, { data: params }, header, "post")
}
