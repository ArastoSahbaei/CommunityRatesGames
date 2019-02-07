import AppRouter from "./AppRouter";
import React from "react";
import ReactDOM from 'react-dom'

const indexRoot = document.getElementById('index_root');
function run() {
    console.log(document.getElementById('root'))
    ReactDOM.render(
        <div><h1>Wojo</h1></div>,
        document.getElementById('root')//indexRoot
    );
}

const loadedStates = ['complete', 'loaded', 'interactive'];

if (loadedStates.includes(document.readyState) && document.body) {
    run();
} else {
    window.addEventListener('DOMContentLoaded', run, false);
}
