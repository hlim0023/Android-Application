package com.example.Goalden;

public class Task {
    private String title;
    private String description;
    private String dueDate;
    private boolean isCompleted;
    private String category;

    public Task(String title, String description, String dueDate, boolean isCompleted, String category) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
        this.category = category;
    }


        // Getters and Setters

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public boolean isCompleted() {
            return isCompleted;
        }

        public void setCompleted(boolean completed) {
            isCompleted = completed;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

