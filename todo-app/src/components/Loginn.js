import React from 'react';
import {connect} from 'react-redux';
import {signIn, signOut} from '../actions'

class GoogleAuth extends React.Component{           //Google Api - Gapi

    componentDidMount(){
        window.gapi.load('client:auth2', () => {
            window.gapi.client.init({
                clientId:
                    '437310296749-fk16m3rael59jmp96m6ms59f6v6cr2ue.apps.googleusercontent.com',
                scope:'email'
            }).then(()=>{                                                       //Returns promise so using then right there!
                this.auth = window.gapi.auth2.getAuthInstance();
                this.onAuthChange(this.auth.isSignedIn.get());                  //isSignedIn.get()--> returns null- true -false // isSignedIn - signIn - signOut --> built-in methods in gapi(GoogleApi)                                         
                this.auth.isSignedIn.listen(this.onAuthChange);                 //sayfayı yenilemeden güncel olarak ekranda kullanıcı login durumunun görünmesi için, built in method-->listen.
            });
        });
    }

    onAuthChange = (isSignedIn) => {
        //this.setState({isSignedIn:this.auth.isSignedIn.get()})
        if(isSignedIn){
            this.props.signIn(this.auth.currentUser.get().getId());         //Send Google Id for store it -->built-in method --> Google Api method
        }else{
            this.props.signOut();
        }
    };

    onSignInClick = () =>{
        this.auth.signIn();      
    }

    onSignOutClick = () =>{
        this.auth.signOut();
    }

    renderAuthButton(){
        if(this.props.isSignedIn===null){
            return null;
        }else if (this.props.isSignedIn){
            return (
                <button onClick={this.onSignOutClick} className="ui red google button">
                    <i className="google icon" />
                    Sign Out
                </button>
            );
        }else{
            return (
                <button onClick={this.onSignInClick} className="ui red google button">
                    <i className="google icon" />
                    Sign In
                </button>
            );
        }
    }

    render(){
        return <div>{this.renderAuthButton()}</div>
    }
}

const mapStateToProps = (state) => {
    return {isSignedIn:state.auth.isSignedIn,userId:state.auth.userId};              //state.auth --> reducers index --> combinereducers... 
};

export default connect(mapStateToProps,{signIn,signOut})(GoogleAuth);

/*
    Console-->

    gapi.auth2.getAuthInstance()
    gapi.auth2.getAuthInstance().signIn()
    refresh ! 
*/