import React from 'react';
import { connect } from 'react-redux';
import { signIn, signOut} from '../actions';
import { Link } from 'react-router-dom';

class Auth extends React.Component {           

  componentDidMount() {
      console.log(this.auth);
  }

  onAuthChange = (isSignedIn) => {
    //this.setState({isSignedIn:this.auth.isSignedIn.get()})
    if(isSignedIn){
        this.props.signIn(this.auth.currentUser.get().getId());         //Send Google Id for store it -->built-in method --> Google Api method
    }else{
        this.props.signOut();
    }
};

  renderAuthButton() {
      return (
        <Link to="/Login" className="ui red google button">
          <i className="google icon" />
          Sign In
      </Link>
      );
  }

  render() {
    return <div>{this.renderAuthButton()}</div>
  }
}

const mapStateToProps = (state) => {
  return { isSignedIn: state.auth.isSignedIn, userId: state.auth.userId };              //state.auth --> reducers index --> combinereducers... 
};

export default connect(mapStateToProps, { signIn, signOut })(Auth);

/*
    Console-->

    gapi.auth2.getAuthInstance()
    gapi.auth2.getAuthInstance().signIn()
    refresh !
*/