import React, { useEffect, useState } from "react";
import { createEmployee, updateEmployee } from "../services/EmployeeService";

function EmployeeForm({ fetchEmployees, editingEmployee, setEditingEmployee }) {

    const [employee, setEmployee] = useState({
        firstName: "",
        lastName: "",
        email: "",
        phoneNumber: "",
        age: "",
        department: ""
    });

    useEffect(() => {
        if (editingEmployee) {
            setEmployee(editingEmployee);
        }
    }, [editingEmployee]);

    const handleChange = (e) => {
        setEmployee({
            ...employee,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const payload = {
            ...employee,
            age: employee.age ? Number(employee.age) : null
        };

        try {
            if (editingEmployee) {
                await updateEmployee(editingEmployee.id, payload);
                alert("Employee Updated Successfully");
            } else {
                await createEmployee(payload);
                alert("Employee Created Successfully");
            }

            setEmployee({
                firstName: "",
                lastName: "",
                email: "",
                phoneNumber: "",
                age: "",
                department: ""
            });

            setEditingEmployee(null);
            fetchEmployees();

        } catch (error) {
    alert(error.message);
}
    };

    return (
        <div>
            <h2>{editingEmployee ? "Update Employee" : "Add Employee"}</h2>

            <form onSubmit={handleSubmit}>

                <input name="firstName" placeholder="First Name"
                    value={employee.firstName} onChange={handleChange} />

                <input name="lastName" placeholder="Last Name"
                    value={employee.lastName} onChange={handleChange} />

                <input name="email" placeholder="Email"
                    value={employee.email} onChange={handleChange} />

                <input name="phoneNumber" placeholder="Phone Number"
                    value={employee.phoneNumber} onChange={handleChange} />

                <input type="number" name="age" placeholder="Age"
                    value={employee.age} onChange={handleChange} />

                <select name="department"
                    value={employee.department}
                    onChange={handleChange}>

                    <option value="">Select Department</option>
                    <option value="HR">HR</option>
                    <option value="IT">IT</option>
                    <option value="FINANCE">FINANCE</option>
                </select>

                <br /><br />

               <button type="submit" className="save-btn">
  {editingEmployee ? "Update Employee" : "Add Employee"}
</button>

            </form>
        </div>
    );
}

export default EmployeeForm;
