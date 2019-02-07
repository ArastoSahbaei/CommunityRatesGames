import {Switch, withRouter} from "react-router-dom";

import AppScreen from "./app/AppScreen";
import home from "./app/screens/Home";
import * as React from "react";




const AppScreenRoutered = withRouter(AppScreen);

export default class AppRouter extends React.Component {
    render () {
        console.log("SHIIIIIT2")
        return (
            <AppScreenRoutered>
                <Switch>
                    <Route
                        path = "/home"
                        component={home}
                    />
                </Switch>
            </AppScreenRoutered>
        )
    }
}