// const HOST = 'http://i7a201.p.ssafy.io:8000/'
const HOST = 'http://localhost:8000/'

const MEMBER = 'member/'
const SOCIAL = 'social/'
const BANK = 'bank/'
const SEARCH = 'search/'
const PRODUCT = 'product/'

export default {
  member: {
    register: () => HOST + MEMBER + 'register',
    login: () => HOST + MEMBER + 'login',
    info: () => HOST + MEMBER + 'info',
    emailcheck: () => HOST + MEMBER + 'emailcheck',
    emailverify: () => HOST + MEMBER + 'emailverify',
    password: () => HOST + MEMBER + 'password',
    status: () => HOST + MEMBER + 'status',
    roles: () => HOST + MEMBER + 'roles',
    social: () => HOST + MEMBER + 'social',
    list: () => HOST + MEMBER + 'list'
  },
  social: {
    google: () => HOST + SOCIAL + 'google',
    googlelogin: () => HOST + SOCIAL + 'google/' + 'login',
    kakao: () => HOST + SOCIAL + 'kakao',
    kakaologin: () => HOST + SOCIAL + 'kakao/' + 'login',
    naver: () => HOST + SOCIAL + 'naver',
    naverlogin: () => HOST + SOCIAL + 'naver/' + 'login'
  },
  bank: {
    banks: () => HOST + BANK,
    bank: bankId => HOST + BANK + bankId
  },
  search: {
    search: () => HOST + SEARCH,
    keyword: () => HOST + SEARCH + 'keyword'
  },
  product: {
    detail: (productId) => HOST + PRODUCT + productId
  }
}