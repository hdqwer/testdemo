export default {
  namespaced: true,
  state: {
    id: 0,
    name: '',
    avatar: null
  },
  mutations: {
    updateId (state, id) {
      state.id = id
    },
    updateName (state, name) {
      state.name = name
    },
    updateAvatar (state, avatar) {
      state.avatar = avatar
    }
  }
}
