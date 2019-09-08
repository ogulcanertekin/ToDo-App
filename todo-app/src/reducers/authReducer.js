import {SET_CURRENT_USER, SIGN_OUT_CURRENT_USER} from '../actions/types';

const INITIAL_STATE = {                     //Capitalize cannot be changed in  engineering...
    isSignedIn:null,
    userId:null,
};  

export default (state = INITIAL_STATE,action) => {
    switch (action.type){
        case SET_CURRENT_USER:
            return {...state,isSignedIn: true, userId: action.payload};             //Update UserId -->coming from action
        case SIGN_OUT_CURRENT_USER:
            return {...state,isSignedIn:false, userId: null};
        default:
            return state;
    }
};