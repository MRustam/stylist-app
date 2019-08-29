import React, {Component} from 'react'
import Faker from 'faker'
import api from '../axios-config'
import {ToastContainer, toast} from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import $ from 'jquery';

class HomePage extends Component {

    constructor(props) {
        super(props);
        this.state = {favors: []}
    }

    componentDidMount() {
        this.fetchFavors()
    }

    fetchFavors = () => {
        api.get('/favor/all')
            .then(response => {
                this.setState({favors: response.data})
            })
            .catch(err => {
                toast.error(err.message, {
                    position: toast.POSITION.TOP_RIGHT
                })
            })
    }

    subscribe = (id) => {
        api.get('/user/subscribe/' + id)
            .then(res => {
                toast.success("Услуга заказанна.", {
                    position: toast.POSITION.TOP_RIGHT
                })
            })
            .catch(err => {
                toast.error(err.message, {
                    position: toast.POSITION.TOP_RIGHT
                })
            })
    }

    handleHover = () => {
        // $('.special.cards .image').dimmer({
        //     on: 'hover'
        // });
    }

    render() {
        return (
            <div className="ui special centered cards">
                {this.state.favors.map((el) => {
                    return (
                        <div className="card">
                            <div className="blurring dimmable image" onMouseEnter={this.handleHover}>
                                {/*<div className="ui dimmer">*/}
                                {/*    <div className="content">*/}
                                {/*        <div className="center">*/}
                                {/*            <div className="ui inverted button">Add Friend</div>*/}
                                {/*        </div>*/}
                                {/*    </div>*/}
                                {/*</div>*/}
                                <img src={Faker.image.avatar()} />
                            </div>
                            <div className="content left aligned">
                                <a className="header">{el.title}</a>
                                <div className="meta">
                                    <span className="date">{el.description}</span><br/>
                                    {/*<span className="date">₽{el.price}</span>*/}
                                </div>
                            </div>
                            <div className="extra content left aligned">
                                <a>
                                    <i className="graduation cap icon"/>
                                    {el.popularity}
                                </a>
                            </div>
                        </div>
                    )
                })}
                <ToastContainer autoClose={3000}/>
            </div>
        )
    }
}

export default HomePage



// <div className="ui grid">
//     <div className="six wide column">
//     <div className="ui large bordered image">
//     {el.isOnSale ?
//             <a className="ui tiny purple right ribbon label">акция</a> : null}
// <img src={Faker.image.avatar()}/>
// </div>
// </div>
// <div className="ten wide column">
// <div className="content">
// <h3 className="header">{el.title}</h3>
// <div className="meta">
// {el.description}
// </div>
// <div className="extra content">
// <div className="ui divider"/>
// <span className="left floated">
// <i className="graduation cap icon"/>
// {el.popularity}
// </span>
// <div className="ui tag labels">
// <a className="ui right floated large blue label" onClick={() => {
// this.subscribe(el.id)
// }}>
// ₽ {el.price}
// </a>
// </div>
// </div>
// </div>
// </div>
// </div>
