export default {
    male: 1,
    female: 0,
    toSexName: (value) => {
        return value === 1 ? '男' : '女'
    },

    NoticeType: {
        INFO: 222,
        APPLY_CONNECT: 1001
    },
    NoticeOption: {
        CONFIRM: 11,
        REFUSED: 22
    }
}
