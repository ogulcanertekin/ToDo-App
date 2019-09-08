import axios from 'axios'
import todos from '../apis/todos'
import setAuthToken from '../utils/setAuthToken'
import jwt_decode from 'jwt-decode'
import { SIGN_IN, SET_CURRENT_USER } from './types'
import { SIGN_OUT } from './types'

// REGISTER
export const registerUser = (userData, history) => {
  todos.post('', userData)
    .then(res => history.push('/login'))
    .catch(err => console.log(err))
}

export function setCurrentUser(userId){
  return{
    type:SET_CURRENT_USER,
    userid:userId
  };
}

// LOGIN
export const signIn = userData => dispatch => {
  todos.post('/login', userData)
    .then(res => {

      const token = res.headers.authorization
      const userId = res.headers.userid
      localStorage.setItem('jwtToken', token)
      setAuthToken(token)
      const decoded = jwt_decode(token)
      console.log(userId);
      dispatch(setCurrentUser(userId))
    })
    .catch(err => console.log(err))
}

export const signOut = () => {

  localStorage.removeItem('jwtToken')

  return {
    type: SIGN_OUT
  };
};
