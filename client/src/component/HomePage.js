import React, {Component} from 'react'
import Faker from 'faker'
import api from '../axios-config'
import {ToastContainer, toast} from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'

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

    render() {
        return (
            <div className="ui divided items">
                {this.state.favors.map((el) => {
                    return(<div id="item" className="item">
                        <div className="ui grid">
                            <div className="seven wide column">
                                <div className="ui small image">
                                    <img src={Faker.image.avatar()}/>
                                </div>
                            </div>
                            <div className="nine wide column">
                                <div className="middle aligned content">
                                    <div className="header">
                                        {el.title}
                                    </div>
                                    <div className="description">
                                        {el.description}
                                    </div>
                                    <div className="extra">
                                        <div className="ui primary button" onClick={() => {
                                            this.subscribe(el.id)
                                        }}>
                                            Интересно
                                            <i className="right chevron icon"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>)
                })}
                <ToastContainer autoClose={3000}/>
            </div>
        )
    }
}

export default HomePage