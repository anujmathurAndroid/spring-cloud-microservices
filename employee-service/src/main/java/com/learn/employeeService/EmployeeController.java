@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(new Employee(id, "Anuj", "IT"));
    }
}
