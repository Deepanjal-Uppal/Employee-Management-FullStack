const BASE_URL = "http://localhost:8081/api/employees";

// GET ALL
export const getEmployees = async () => {
    const response = await fetch(BASE_URL);
    return response.json();
};

// CREATE
export const createEmployee = async (employee) => {
    const response = await fetch(BASE_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(employee)
    });

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText);
    }

    return response.json();
};

// UPDATE
export const updateEmployee = async (id, employee) => {
    const response = await fetch(`${BASE_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(employee)
    });

    if (!response.ok) {
        const errorText = await response.text();
        throw new Error(errorText);
    }

    return response.json();
};


// DELETE
export const deleteEmployee = async (id) => {
    const response = await fetch(`${BASE_URL}/${id}`, {
        method: "DELETE"
    });

    if (!response.ok) {
        throw new Error("Failed to delete employee");
    }
};
