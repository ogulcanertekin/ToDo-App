import axios from 'axios'
import todos from '../apis/todos'
import setAuthToken from '../utils/setAuthToken'
import jwt_decode from 'jwt-decode'
import {
    SET_CURRENT_USER,
    SIGN_OUT_CURRENT_USER,
    CREATE_TODO_LIST,
    CREATE_TODO_ITEM,
    FETCH_TODO_LISTS,
    FETCH_TODO_ITEMS,
    UPDATE_TODO_ITEM_STATUS,
} from './types'


// REGISTER
export const registerUser = (userData, history) => {
    todos.post('', userData)
        .then(res => history.push('/login'))
        .catch(err => console.log(err))
}

export const setCurrentUser = userId => {
    return {
        type: SET_CURRENT_USER,
        payload: userId
    };
};

// LOGIN
export const signIn = (userData) => dispatch => {
    todos.post('/login', userData)
        .then(res => {
            const token = res.headers.authorization
            const  userId  = res.headers.userid
            localStorage.setItem('jwtToken', token)
            setAuthToken(token)
            const decoded = jwt_decode(token)
            dispatch(setCurrentUser(userId))
          })
          .catch(err => console.log(err))}

export const signOut = () => {

    localStorage.removeItem('jwtToken')

    return {
        type: SIGN_OUT_CURRENT_USER
    };
};