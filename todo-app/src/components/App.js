import React from 'react';
import {Router, Route,Switch} from 'react-router-dom';          
import history from '../history';                               //Kullanıcıların hangi sayfaya baktıgını ögrenebilmek ve örn post işleminden sonra programming navigate yapabilmek için gerekli..
import Header from './Header';
import Auth from '../components/Auth'
import Login from '../components/Login'

const App = () => {                                             //Client-Side Routing!                         
    return (                
        <div className="ui container">               
            <Router history={history}>
                <div>                                       {/* Always Visible Component Like Layout */}
                    <Auth/>
                    <Switch>
                    <Route path="/login" exact component={Login}/>
                    </Switch>   
                </div>
            </Router>
        </div>
    );
};

export default App;