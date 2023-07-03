// App.js
import React from "react";
import { BrowserRouter, Switch, Route, Routes } from "react-router-dom";
import { TaskList, TaskDetails } from "./components";

export default function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path="/" element={<TaskList />} />
          <Route path="/tasks" element={<TaskList />} />
          <Route path="/tasks/:taskId" element={<TaskDetails />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}
