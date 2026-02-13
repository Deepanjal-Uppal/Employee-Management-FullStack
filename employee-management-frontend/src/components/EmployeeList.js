import React from "react";
import { deleteEmployee } from "../services/EmployeeService";

function EmployeeList({ employees, fetchEmployees, setEditingEmployee }) {

    const handleDelete = async (id) => {
        await deleteEmployee(id);
        fetchEmployees();
    };

    return (
        <div>
            <h2>Employee List</h2>

            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Email</th>
                        <th>Phone</th> 
                        <th>Department</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                    {employees.map(emp => (
                        <tr key={emp.id}>
                            <td>{emp.id}</td>
                            <td>{emp.firstName}</td>
                            <td>{emp.email}</td>
                            <td>{emp.phoneNumber}</td>
                            <td>{emp.department}</td>
                            <td>
                                <button
  className="edit-btn"
  onClick={() => setEditingEmployee(emp)}
>
  Edit
</button>

<button
  className="delete-btn"
  onClick={() => handleDelete(emp.id)}
>
  Delete
</button>

                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default EmployeeList;
