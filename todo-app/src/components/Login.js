import React from 'react';
import { connect } from 'react-redux';
import { signIn, signOut } from '../actions';
import { Link } from 'react-router-dom';

class Login extends React.Component {

    constructor() {
        super()
        this.state = {
            email: '',
            password: ''
        };

    }

    onSubmit = e => {
        e.preventDefault();
        const userData = {
            email: this.state.email,
            password: this.state.password
        }
        this.props.signIn(userData)
    }

    onChange = e => {
        this.setState({ [e.target.name]: e.target.value })
      }

    render() {
        return(
            <div>
				<form class="ui form" onSubmit={this.onSubmit}>
					<div class="field">
                        <label>Email</label>
						<input
                            type="text"
                            placeholder="Email"
                            name="email"
                            value={this.state.email}
                            onChange={this.onChange}
                        />
                    </div>
					<div class="field">
                        <label>Password</label>
						<input 
                            type="password"
                            placeholder="Password"
                            name="password"
                            value={this.state.password}
                            onChange={this.onChange}
                        />
                    </div>
                    <div class="field">
                        <div class="ui checkbox">
                            <input type="checkbox" tabindex="0" class="hidden"/>
                            <label>
                                I agree to the Terms and Conditions
                            </label>
                        </div>
                    </div>
                    <button class="ui button" type="submit">Submit</button>
				</form>
            </div>                        
        )
    }
}

const mapStateToProps = state => ({
    auth: state.auth,
})

export default connect(mapStateToProps, { signIn })(Login)


