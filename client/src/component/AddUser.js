import React from 'react';
import SkyLight from 'react-skylight';

class AddUser extends React.Component {

    constructor(props) {
        super(props);
        this.state = {firstName: '', lastName: '', age: '', phone: '', email: ''};
    }

    // Save car and close modal form
    handleSubmit = (event) => {
        event.preventDefault();
        this.props.addUser({
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            age: this.state.age,
            phone: this.state.phone,
            email: this.state.email
        });
        this.refs.addDialog.hide();
    }

    cancelSubmit = (event) => {
        event.preventDefault();
        this.refs.addDialog.hide();
    }

    handleChange = (event) => {
        this.setState(
            {[event.target.name]: event.target.value}
        );
    }

    render() {
        return (
            <div>
                <SkyLight hideOnOverlayClicked ref="addDialog">
                    <h3>Добавить пользователя</h3>
                    <form>
                        <input type="text" placeholder="Имя" name="firstName" onChange={this.handleChange}/><br/>
                        <input type="text" placeholder="Фамилия" name="lastName" onChange={this.handleChange}/><br/>
                        <input type="text" placeholder="Возраст" name="age" onChange={this.handleChange}/><br/>
                        <input type="text" placeholder="Телефон" name="phone" onChange={this.handleChange}/><br/>
                        <input type="text" placeholder="Почта" name="email" onChange={this.handleChange}/><br/>
                        <button onClick={this.handleSubmit}>Сохранить</button>
                        <button onClick={this.cancelSubmit}>Отмена</button>
                    </form>
                </SkyLight>
                <div>
                    <button style={{'margin': '10px'}} onClick={() => this.refs.addDialog.show()}>Добавить</button>
                </div>
            </div>
        );
    }
}

export default AddUser;