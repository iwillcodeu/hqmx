import VueI18n from 'vue-i18n'//需先安装vue-i18n
import Vue from 'vue'

Vue.use(VueI18n)

var langMessages = {
    cn: require("../../language/zh-cn.json"),
    en: require("../../language/en-us.json")
}

var i18n = new VueI18n({
    locale: 'en',
    messages: langMessages
})
console.log(langMessages.en);
export default i18n
