import React from "react";

const PatientTable = ({
  patients,
  editingId,
  editingRow,
  onStartEdit,
  onCancelEdit,
  onEditChange,
  onSaveEdit,
  onDelete,
}) => {
  if (!patients || patients.length === 0) {
    return <p>No patients found.</p>;
  }

  return (
    <div className="table-wrapper">
      <table className="patient-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>First</th>
            <th>Last</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Zip</th>
            <th>Phone</th>
            <th>Email</th>
            <th style={{ minWidth: "140px" }}>Actions</th>
          </tr>
        </thead>
        <tbody>
          {patients.map((p) => {
            const isEditing = editingId === p.id;

            return (
              <tr key={p.id}>
                <td>{p.id}</td>

                <td>
                  {isEditing ? (
                    <input
                      name="firstName"
                      value={editingRow.firstName}
                      onChange={onEditChange}
                    />
                  ) : (
                    p.firstName
                  )}
                </td>

                <td>
                  {isEditing ? (
                    <input
                      name="lastName"
                      value={editingRow.lastName}
                      onChange={onEditChange}
                    />
                  ) : (
                    p.lastName
                  )}
                </td>

                <td>
                  {isEditing ? (
                    <input
                      name="address"
                      value={editingRow.address}
                      onChange={onEditChange}
                    />
                  ) : (
                    p.address
                  )}
                </td>

                <td>
                  {isEditing ? (
                    <input
                      name="city"
                      value={editingRow.city}
                      onChange={onEditChange}
                    />
                  ) : (
                    p.city
                  )}
                </td>

                <td>
                  {isEditing ? (
                    <input
                      name="state"
                      value={editingRow.state}
                      onChange={onEditChange}
                    />
                  ) : (
                    p.state
                  )}
                </td>

                <td>
                  {isEditing ? (
                    <input
                      name="zipCode"
                      value={editingRow.zipCode}
                      onChange={onEditChange}
                    />
                  ) : (
                    p.zipCode
                  )}
                </td>

                <td>
                  {isEditing ? (
                    <input
                      name="phoneNumber"
                      value={editingRow.phoneNumber}
                      onChange={onEditChange}
                    />
                  ) : (
                    p.phoneNumber
                  )}
                </td>

                <td>
                  {isEditing ? (
                    <input
                      name="email"
                      value={editingRow.email}
                      onChange={onEditChange}
                    />
                  ) : (
                    p.email
                  )}
                </td>

                <td className="actions-cell">
                  {isEditing ? (
                    <>
                      <button
                        className="btn btn-small btn-primary"
                        onClick={() => onSaveEdit(p.id)}
                      >
                        Save
                      </button>
                      <button
                        className="btn btn-small btn-secondary"
                        onClick={onCancelEdit}
                      >
                        Cancel
                      </button>
                    </>
                  ) : (
                    <>
                      <button
                        className="btn btn-small"
                        onClick={() => onStartEdit(p)}
                      >
                        Edit
                      </button>
                      <button
                        className="btn btn-small btn-danger"
                        onClick={() => onDelete(p.id)}
                      >
                        Delete
                      </button>
                    </>
                  )}
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default PatientTable;