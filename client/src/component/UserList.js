import React, {Component} from 'react'
import ReactTable from 'react-table'
import 'react-table/react-table.css'
import {SERVER_URL} from '../constan'
import {confirmAlert} from 'react-confirm-alert'
import 'react-confirm-alert/src/react-confirm-alert.css'
import {ToastContainer, toast} from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import AddUser from './AddUser'
import api from '../axios-config'

class UserList extends Component {
    constructor(props) {
        super(props);
        this.state = {users: [], page: 0, size: 20}
    }

    componentDidMount() {
        this.fetchUsers();
    }

    // Fetch all users
    fetchUsers = () => {
        api.get('/user/all?page=' + this.state.page + '&size=' + this.state.size)
            .then(response => {
                this.setState({
                    users: response.data.content
                })
            })
            .catch(err => {
                toast.error(err, {
                    position: toast.POSITION.TOP_RIGHT
                })
            })
    }

    // Modal confirm Delete window
    confirmDelete = (id) => {
        confirmAlert({
            title: 'Удаление пользователя',
            message: 'Действительно, удалить?',
            buttons: [
                {label: 'Удалить', onClick: () => this.onDelClick(id)},
                {label: 'Отменить',}
            ]
        })
    }

    // Add new User
    addUser(user) {
        api.post('/user/save/simple', user)
            .then(res => {
                toast.success("Пользователь создан.", {
                    position: toast.POSITION.TOP_RIGHT
                });
                this.fetchUsers()
            })
            .catch(err => {
                toast.error(err.message, {
                    position: toast.POSITION.TOP_RIGHT
                });
            })
    }

    // Disable user
    onDelClick = (id) => {
        fetch(SERVER_URL + '/user/enabled/' + id + '/' + false,
            {method: 'PATCH'}
        ).then(res => {
            toast.success("Пользователь удален.", {
                position: toast.POSITION.TOP_RIGHT
            });
            this.fetchUsers()
        }).catch(err => {
            toast.error("Ошибка удаления.", {
                position: toast.POSITION.TOP_RIGHT
            });
        })
    }

    render() {
        const columns = [
            {
                Header: 'Имя',
                accessor: 'firstName'
            }, {
                Header: 'Фамилия',
                accessor: 'lastName'
            }, {
                Header: 'Возраст',
                accessor: 'age'
            }, {
                Header: 'Телефон',
                accessor: 'phone'
            }, {
                Header: 'Почта',
                accessor: 'email'
            }, {
                Header: 'Создан',
                accessor: 'created'
            }, {
                id: 'delbutton',
                sortable: false,
                filterable: false,
                width: 100,
                accessor: 'id',
                Cell: ({value}) => (
                    <button onClick={() => {
                        this.confirmDelete(value)
                    }}>Удалить</button>
                )
            }
        ]
        return (
            <div className="App">
                <AddUser addUser={this.addUser}/>
                <ReactTable data={this.state.users} columns={columns} filterable={false} sortable={false}
                            pageSize={this.state.size}/>
                <ToastContainer autoClose={2000}/>
            </div>
        )
    }
}

export default UserList